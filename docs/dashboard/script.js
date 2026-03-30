const ctx = document.getElementById('grafico').getContext('2d');

let chart;

async function carregar(tipo) {

    const res = await fetch(`../../resultados/${tipo}.csv`);
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

    if (chart) chart.destroy();

    chart = new Chart(ctx, {
        type: 'line',
        data: {
            datasets: Object.keys(dados).map((alg, i) => ({
                label: alg,
                data: dados[alg],
                borderWidth: 2
            }))
        }
    });
}

document.getElementById("tipo").addEventListener("change", (e) => {
    carregar(e.target.value);
});

carregar("ordenacao");