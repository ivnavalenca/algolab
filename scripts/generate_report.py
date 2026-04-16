import os
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, Image
from reportlab.lib.styles import getSampleStyleSheet

PDF = "docs/relatorio.pdf"
IMG = "docs/comparacao.png"

styles = getSampleStyleSheet()
doc = SimpleDocTemplate(PDF)

conteudo = []

conteudo.append(Paragraph("Relatório Algolab", styles["Title"]))
conteudo.append(Spacer(1, 12))

conteudo.append(Paragraph("Análise automática de desempenho.", styles["Normal"]))
conteudo.append(Spacer(1, 12))

if os.path.exists(IMG):
    conteudo.append(Image(IMG, width=400, height=250))

doc.build(conteudo)

print("PDF gerado:", PDF)