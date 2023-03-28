import pandas as pd
import numpy as np
import statsmodels.api as sm
import matplotlib.pyplot as plt

# Read in your time series data as a Pandas DataFrame
data = pd.read_csv('time_series_data.csv')

# Convert the index to a datetime object
data.index = pd.to_datetime(data.index)

# Fit an ARIMA model to the data
model = sm.tsa.ARIMA(data, order=(1,1,1))
results = model.fit()

# Generate forecasts for the next 12 periods
forecast = results.forecast(steps=12)

# Create a new DataFrame with the forecasted values
forecast_df = pd.DataFrame(forecast[0], index=pd.date_range(start=data.index[-1], periods=12, freq='M'), columns=['Forecast'])

# Plot the actual and forecasted values using Matplotlib
plt.figure(figsize=(12,6))
plt.plot(data, label='Actual')
plt.plot(forecast_df, label='Forecast')
plt.legend()
plt.title('ARIMA Forecast')
plt.xlabel('Date')
plt.ylabel('Index')
plt.show()
