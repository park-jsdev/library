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
# The ARIMA model forecasts the next 10 days, and plots it using pyplot.

import pandas as pd
import matplotlib.pyplot as plt
from statsmodels.tsa.arima.model import ARIMA

# Load data
data = pd.read_csv('data.csv')
data['date'] = pd.to_datetime(data['date'])
data.set_index('date', inplace=True)

# Train ARIMA model
model = ARIMA(data, order=(1, 1, 1))
model_fit = model.fit()

# Forecast the next 10 days
future_dates = pd.date_range(start='1/15/2022', periods=10, freq='D')
future_pred = model_fit.forecast(steps=10)

# Plot the predicted values
future_dates_df = pd.DataFrame({'date': future_dates, 'predicted_value': future_pred})

fig, ax = plt.subplots()
ax.plot(data.index, data['value'], label='actual values')
ax.plot(future_dates_df['date'], future_dates_df['predicted_value'], label='predicted values')
ax.legend()

# Set the x-axis limits to show only the relevant days
start_date = data.index.max() - pd.DateOffset(days=30)
end_date = future_dates.max() + pd.DateOffset(days=1)
ax.set_xlim(start_date, end_date)

plt.show()
