/*
 * ============================================================
 * ALGOLAB DASHBOARD - SCRIPT COMPLETO FINAL
 * ============================================================
 */

let chart;

/*
 * ============================================================
 * CARREGAR CSV (MULTI-CENÁRIO)
 * ============================================================
 */
async function carregarCSV(arquivo) {

    const res = await fetch(`resultados/historico/${arquivo}`);
    const texto = await res.text();

    const linhas = texto.split("\n").slice(1);

    const dados = {};

    linhas.forEach(l => {

        if (!l) return;

        const [tamanho, cenario, algoritmo, tempo] = l.split(",");

        if (!dados[cenario]) dados[cenario] = {};
        if (!dados[cenario][algoritmo]) dados[cenario][algoritmo] = [];

        dados[cenario][algoritmo].push({
            x: Number(tamanho),
            y: Number(tempo)
        });
    });

    return dados;
}

/*
 * ============================================================
 * POPULAR CENÁRIOS
 * ============================================================
 */
function popularCenarios(dados) {

    const select = document.getElementById("cenario");
    select.innerHTML = "";

    Object.keys(dados).forEach(c => {
        select.add(new Option(c, c));
    });
}

/*
 * ============================================================
 * REGRESSÃO LOG-LOG + R²
 * ============================================================
 */
function regressao(dados) {

    const xs = dados.map(p => Math.log(p.x));
    const ys = dados.map(p => Math.log(p.y));

    const n = xs.length;

    let sx = 0, sy = 0, sxy = 0, sx2 = 0;

    for (let i = 0; i < n; i++) {
        sx += xs[i];
        sy += ys[i];
        sxy += xs[i] * ys[i];
        sx2 += xs[i] * xs[i];
    }

    const k = (n * sxy - sx * sy) / (n * sx2 - sx * sx);
    const logC = (sy - k * sx) / n;
    const c = Math.exp(logC);

    const mediaY = sy / n;

    let ssTot = 0, ssRes = 0;

    for (let i = 0; i < n; i++) {

        const yPrev = logC + k * xs[i];

        ssTot += Math.pow(ys[i] - mediaY, 2);
        ssRes += Math.pow(ys[i] - yPrev, 2);
    }

    const r2 = 1 - (ssRes / ssTot);

    return { k, c, r2 };
}

/*
 * ============================================================
 * PREVISÃO
 * ============================================================
 */
function preverTempo(dados, n) {
    const { k, c } = regressao(dados);
    return c * Math.pow(n, k);
}

/*
 * ============================================================
 * RANKING
 * ============================================================
 */
function calcularRanking(dados) {

    const ranking = [];

    Object.keys(dados).forEach(alg => {

        const t = preverTempo(dados[alg], 100000);

        ranking.push({ algoritmo: alg, tempo: t });
    });

    ranking.sort((a, b) => a.tempo - b.tempo);

    return ranking;
}

/*
 * ============================================================
 * ALERTAS DE REGRESSÃO
 * ============================================================
 */
function detectarRegressao(a, b) {

    const alertas = [];

    Object.keys(b).forEach(alg => {

        if (!a[alg]) return;

        const tA = preverTempo(a[alg], 100000);
        const tB = preverTempo(b[alg], 100000);

        const diff = (tB - tA) / tA;

        if (diff > 0.15) {
            alertas.push({ algoritmo: alg, diff });
        }
    });

    return alertas;
}

/*
 * ============================================================
 * RENDER
 * ============================================================
 */
function renderRanking(dados) {

    const ul = document.getElementById("ranking");
    ul.innerHTML = "";

    calcularRanking(dados).forEach((r, i) => {

        const li = document.createElement("li");
        li.textContent = `${i+1}º ${r.algoritmo}`;

        ul.appendChild(li);
    });
}

function renderAlertas(a, b) {

    const div = document.getElementById("alertas");
    div.innerHTML = "";

    const alertas = detectarRegressao(a, b);

    if (!alertas.length) {
        div.innerHTML = "✅ Sem regressões";
        return;
    }

    alertas.forEach(a => {
        div.innerHTML += `❌ ${a.algoritmo} piorou<br>`;
    });
}

function renderModelo(dados) {

    const div = document.getElementById("complexidade");
    div.innerHTML = "";

    Object.keys(dados).forEach(alg => {

        const { k, r2 } = regressao(dados[alg]);

        div.innerHTML += `${alg}: n^${k.toFixed(2)} (R²=${r2.toFixed(2)})<br>`;
    });
}

function renderPrevisao(dados) {

    const div = document.getElementById("previsao");
    div.innerHTML = "";

    Object.keys(dados).forEach(alg => {

        const t = preverTempo(dados[alg], 100000);

        div.innerHTML += `${alg}: ${Math.round(t)}<br>`;
    });
}

/*
 * ============================================================
 * COMPARAÇÃO PRINCIPAL
 * ============================================================
 */
async function comparar() {

    const a = document.getElementById("execucaoA").value;
    const b = document.getElementById("execucaoB").value;

    const dadosA_all = await carregarCSV(a);
    const dadosB_all = await carregarCSV(b);

    popularCenarios(dadosB_all);

    const cenario = document.getElementById("cenario").value ||
                    Object.keys(dadosB_all)[0];

    const dadosA = dadosA_all[cenario];
    const dadosB = dadosB_all[cenario];

    const datasets = [];

    Object.keys(dadosB).forEach(alg => {

        // dados reais
        datasets.push({
            label: `${alg}`,
            data: dadosB[alg],
            borderWidth: 2
        });

        // curva ajustada
        const { k, c } = regressao(dadosB[alg]);

        const curva = dadosB[alg].map(p => ({
            x: p.x,
            y: c * Math.pow(p.x, k)
        }));

        datasets.push({
            label: `${alg} (fit)`,
            data: curva,
            borderDash: [5, 5]
        });
    });

    if (chart) chart.destroy();

    const ctx = document.getElementById("grafico").getContext("2d");

    chart = new Chart(ctx, {
        type: "line",
        data: { datasets }
    });

    renderRanking(dadosB);
    renderAlertas(dadosA, dadosB);
    renderModelo(dadosB);
    renderPrevisao(dadosB);
}

/*
 * ============================================================
 * INICIALIZAÇÃO
 * ============================================================
 */
async function iniciar() {

    const res = await fetch("resultados/historico/index.json");
    const arquivos = await res.json();

    const selA = document.getElementById("execucaoA");
    const selB = document.getElementById("execucaoB");

    arquivos.forEach(a => {
        selA.add(new Option(a.arquivo, a.arquivo));
        selB.add(new Option(a.arquivo, a.arquivo));
    });

    selA.value = arquivos[0].arquivo;
    selB.value = arquivos[arquivos.length - 1].arquivo;

    comparar();
}

iniciar();