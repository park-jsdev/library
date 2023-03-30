# This example uses a csv file with the format:
#
# date        value
#--------------------
# 1/1/2022    10
# 1/1/2022    10
# 1/1/2022    10
# ...
# 1/1/2022    10
# with 11 rows, and the date values are strings
# The Autoregression model forecasts the next 10 days and plots it using pyplot.
# Adjusting the lags can control the performance. Higher lags can result in overfitting and vice versa.

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from statsmodels.tsa.ar_model import AutoReg

# Load data
data = pd.read_csv('data.csv')
data['date'] = pd.to_datetime(data['date'])

# Extract the relevant datetime components as input features
data['year'] = data['date'].dt.year
data['month'] = data['date'].dt.month
data['day'] = data['date'].dt.day

# Split the data into training and testing sets
train = data[:len(data)-12]
test = data[len(data)-12:]

# Train autoregression model
model = AutoReg(train['value'], lags=2)
model_fit = model.fit()

# Predict the next 10 days
predictions = model_fit.predict(start=len(train), end=len(train)+9, dynamic=False)

# Plot the predicted values
future_dates = pd.date_range(start='1/15/2022', periods=10, freq='D')
future_dates_df = pd.DataFrame({'date': future_dates, 'predicted_value': predictions})
plt.plot(data['date'], data['value'], label='actual values')
plt.plot(future_dates_df['date'], future_dates_df['predicted_value'], label='predicted values')
plt.xlim(pd.Timestamp('2022-01-01'), pd.Timestamp('2022-01-31'))
plt.legend()
plt.show()
