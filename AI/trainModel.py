import tensorflow as tf
from tensorflow.keras import layers, models
from loadDB import *

model = models.Sequential([
    layers.Dense(64, activation='relu', input_shape=(3,)),  # 3 input features
    layers.Dense(32, activation='relu'),
    layers.Dense(10, activation='softmax')  # 10 classes for classification
])

model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',  # Since labels are integers
              metrics=['accuracy'])

history = model.fit(X, y, epochs=10, validation_data=(X_eval, y_eval))

prediction = model.predict([[60, 100, 210]])
prediction = np.argmax(prediction, axis=1)
print(prediction)