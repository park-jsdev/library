# app.py
import gradio as gr
from fastai.vision.all import *

learn = load_learner('model.pkl')

def predict(uploaded_image):
    try:
        img = PILImage.create(uploaded_image)
        pred, pred_idx, probs = learn.predict(img)

        # Get the original labels from the learner's vocabulary
        labels = learn.dls.vocab
        
        # Map the original labels to more descriptive labels
        label_map = {'lung_n': 'Normal', 'lung_aca': 'Adenocarcinoma'}
        descriptive_labels = [label_map[label] for label in labels]

        return {descriptive_labels[i]: float(probs[i]) for i in range(len(labels))}
    except Exception as e:
        return {"error": str(e)}

title = "Lung Cancer Histopathology Classifier"
description = "A lung cancer histopathology classifier ResNet 34 model trained on the Lung and Colon Cancer Histopathological Images dataset on Kaggle with fastai. Uses Gradio as the interface."
article = "<p style='text-align: center'><a href='https://www.kaggle.com/datasets/andrewmvd/lung-and-colon-cancer-histopathological-images' target='_blank'>Dataset</a></p>"

interface = gr.Interface(
    fn=predict,
    inputs=gr.Image(type="pil"),
    outputs=gr.Label(num_top_classes=2),
    title=title,
    description=description,
    article=article
).launch()
