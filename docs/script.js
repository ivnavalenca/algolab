/*
 * ============================================================
 * 🚀 ALGOLAB DASHBOARD - SCRIPT COMPLETO
 * ============================================================
 *
 * RESPONSABILIDADES:
 * - Controle de cenário
 * - Carregamento de dados (JSON)
 * - Renderização (métricas, ranking, timeline)
 * - Destaques visuais (melhor algoritmo)
 * - Animações
 * - Badges
 *
 * ============================================================
 */

let chart;

/*
 * ============================================================
 * ✨ ANIMAÇÃO
 * ============================================================
 */
function animarElemento(id) {

    const el = document.getElementById(id);

    if (!el) return;

    el.classList.remove("show");
    el.classList.add("fade");

    setTimeout(() => {
        el.classList.add("show");
    }, 50);
}

/*
 * ============================================================
 * 🎯 TROCA DE CENÁRIO
 * ============================================================
 */
function trocarCenario() {

    const cenario = document.getElementById("cenarioSelect").value;

    // gráficos
    document.getElementById("grafico").src =
        `resultados/graficos/${cenario}.png`;

    document.getElementById("boxplot").src =
        `resultados/graficos/boxplot_${cenario}.png`;

    // animação
    animarElemento("grafico");
    animarElemento("boxplot");
    animarElemento("ranking");
    animarElemento("metrics");

    carregarMetrics(cenario);
    carregarRanking(cenario);
    carregarTimeline(cenario);
}

/*
 * ============================================================
 * 📊 MÉTRICAS
 * ============================================================
 */
async function carregarMetrics(cenario) {

    const res = await fetch(`resultados/${cenario}_metrics.json`);
    const data = await res.json();

    let html = "<tr><th>Algoritmo</th><th>k</th><th>R²</th></tr>";

    Object.entries(data).forEach(([alg, info]) => {
        html += `
            <tr>
                <td>${alg}</td>
                <td>${info.k}</td>
                <td>${info.r2}</td>
            </tr>
        `;
    });

    document.getElementById("metrics").innerHTML = html;
}

/*
 * ============================================================
 * 🏆 RANKING + DESTAQUE
 * ============================================================
 */
async function carregarRanking(cenario) {

    const res = await fetch(`resultados/${cenario}_ranking.json`);
    const data = await res.json();

    const lista = Object.entries(data).map(([alg, info]) => ({
        algoritmo: alg,
        score: info.score,
        status: info.status
    }));

    lista.sort((a, b) => b.score - a.score);

    const container = document.getElementById("ranking");
    container.innerHTML = "";

    lista.forEach((item, i) => {

        let classe = "ok";
        if (item.status === "REGRESSAO") classe = "regressao";
        if (item.status === "INSTAVEL") classe = "instavel";
        if (item.status === "COMPLEXO") classe = "complexo";

        const destaque = i === 0 ? "🏆 " : "";
        const estilo = i === 0 ? "ranking-item best" : "ranking-item";

        container.innerHTML += `
            <div class="${estilo} ${classe}">
                ${destaque}${i+1}º - ${item.algoritmo}
                | Score: ${item.score.toFixed(3)}
            </div>
        `;
    });
}

/*
 * ============================================================
 * 📈 TIMELINE
 * ============================================================
 */
async function carregarTimeline(cenario) {

    const res = await fetch(`resultados/${cenario}_timeline.json`);
    const data = await res.json();

    const datasets = [];

    Object.entries(data).forEach(([alg, pontos]) => {

        datasets.push({
            label: alg,
            data: pontos.map(p => ({ x: p.data, y: p.tempo })),
            fill: false
        });
    });

    if (chart) chart.destroy();

    chart = new Chart(document.getElementById("timeline"), {
        type: "line",
        data: { datasets },
        options: {
            parsing: false,
            scales: {
                x: { type: "category" }
            }
        }
    });
}

/*
 * ============================================================
 * 🏷️ BADGES
 * ============================================================
 */
async function carregarBadges() {

    const arquivos = [
        "badge_score.json",
        "badge_maturity.json",
        "badge_regressao.json",
        "badge_best.json",
        "badge_trend.json",
        "badge_quality.json"
    ];

    const container = document.getElementById("badges");
    if (!container) return;

    container.innerHTML = "";

    for (const file of arquivos) {
        try {
            const res = await fetch(`resultados/${file}`);
            const data = await res.json();

            container.innerHTML += `
                <span>
                    ${data.label}: <b>${data.message}</b>
                </span>
            `;
        } catch (e) {}
    }
}

/*
 * ============================================================
 * 🚀 INIT
 * ============================================================
 */
document
    .getElementById("cenarioSelect")
    .addEventListener("change", trocarCenario);

trocarCenario();
carregarBadges();