/*
 * ============================================================
 * DASHBOARD ALGOLAB - SCRIPT FINAL
 * ============================================================
 *
 * OBJETIVO:
 * - Carregar histórico (index.json)
 * - Comparar execuções
 * - Gerar gráfico
 * - Calcular ranking automático
 *
 * ============================================================
 */

const ctx = document.getElementById('grafico').getContext('2d');
let chart;

/*
 * ============================================================
 * CARREGAR CSV
 * ============================================================
 */
async function carregarCSV(arquivo) {

    const res = await fetch(`resultados/historico/${arquivo}`);
    const texto = await res.text();

    const linhas = texto.split("\n").slice(1);

    const dados = {};

    linhas.forEach(l => {

        if (!l) return;

        const [tamanho, , algoritmo, tempo] = l.split(",");

        if (!dados[algoritmo]) {
            dados[algoritmo] = [];
        }

        dados[algoritmo].push({
            x: Number(tamanho),
            y: Number(tempo)
        });
    });

    return dados;
}

/*
 * ============================================================
 * CALCULAR RANKING
 * ============================================================
 */
function calcularRanking(dados) {

    const ranking = [];

    Object.keys(dados).forEach(alg => {

        const valores = dados[alg].map(p => p.y);
        const media = valores.reduce((a, b) => a + b, 0) / valores.length;

        ranking.push({ algoritmo: alg, media });
    });

    ranking.sort((a, b) => a.media - b.media);

    return ranking;
}

/*
 * ============================================================
 * RENDERIZAR RANKING
 * ============================================================
 */
function renderRanking(ranking) {

    const ul = document.getElementById("ranking");
    ul.innerHTML = "";

    ranking.forEach((r, i) => {

        const li = document.createElement("li");

        li.textContent = `${i + 1}º - ${r.algoritmo} (${r.media.toFixed(2)})`;

        if (i === 0) {
            li.style.color = "green";
            li.style.fontWeight = "bold";
        }

        if (i === ranking.length - 1) {
            li.style.color = "red";
        }

        ul.appendChild(li);
    });
}

/*
 * ============================================================
 * COMPARAR EXECUÇÕES
 * ============================================================
 */
async function comparar() {

    const a = document.getElementById("execucaoA").value;
    const b = document.getElementById("execucaoB").value;

    const dadosA = await carregarCSV(a);
    const dadosB = await carregarCSV(b);

    const datasets = [];

    /*
     * Execução A (linha contínua)
     */
    Object.keys(dadosA).forEach(alg => {

        datasets.push({
            label: `${alg} (A)`,
            data: dadosA[alg],
            borderWidth: 2
        });
    });

    /*
     * Execução B (linha tracejada)
     */
    Object.keys(dadosB).forEach(alg => {

        datasets.push({
            label: `${alg} (B)`,
            data: dadosB[alg],
            borderDash: [5, 5],
            borderWidth: 2
        });
    });

    if (chart) chart.destroy();

    chart = new Chart(ctx, {
        type: 'line',
        data: { datasets }
    });

    /*
     * Ranking baseado na execução mais recente (B)
     */
    const ranking = calcularRanking(dadosB);
    renderRanking(ranking);
}

/*
 * ============================================================
 * CARREGAR LISTA DE EXECUÇÕES (index.json)
 * ============================================================
 */
async function carregarLista() {

    const res = await fetch("resultados/historico/index.json");
    const arquivos = await res.json();

    const selA = document.getElementById("execucaoA");
    const selB = document.getElementById("execucaoB");

    arquivos.forEach(a => {
        selA.add(new Option(a, a));
        selB.add(new Option(a, a));
    });

    /*
     * Seleciona automaticamente as duas últimas execuções
     */
    if (arquivos.length >= 2) {
        selA.value = arquivos[arquivos.length - 2];
        selB.value = arquivos[arquivos.length - 1];
        comparar();
    }
}

/*
 * ============================================================
 * INICIALIZAÇÃO
 * ============================================================
 */
carregarLista();