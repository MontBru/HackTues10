from flask import Flask, jsonify, request
import tensorflow as tf
import keras
from keras.models import load_model
import numpy as np

app = Flask(__name__) 


model = tf.keras.models.load_model('pulse_mood_classifier.keras')


@app.route("/get_evaluation")
def test():
    min_avg = int(request.args.get('min_avg'))
    max_avg = int(request.args.get('max_avg'))
    curr_bpm = int(request.args.get('curr_bpm'))

    prediction = model.predict([[min_avg,max_avg, curr_bpm]])

    predicted_class = np.argmax(prediction)
    return jsonify({"evaluation":int(predicted_class)})

if __name__ == "__main__":
    app.run(debug=True)