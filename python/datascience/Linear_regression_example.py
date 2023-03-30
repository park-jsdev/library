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
# The Linear regression model forecasts the next 9 days with the 11 days starting from 1/1/2022, and plots it using pyplot.

import pandas as pd
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt

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

# Define the input and output variables
X_train = train[['year', 'month', 'day']]
y_train = train['value']
X_test = test[['year', 'month', 'day']]
y_test = test['value']

# Train linear regression model
model = LinearRegression()
model.fit(X_train, y_train)

# Predict the next 10 days
future_dates = pd.date_range(start='1/1/2022', periods=20, freq='D')
X_future = pd.DataFrame({'year': future_dates.year, 'month': future_dates.month, 'day': future_dates.day})
future_pred = model.predict(X_future)

# Plot the predicted values
future_dates_df = pd.DataFrame({'date': future_dates, 'predicted_value': future_pred})

plt.plot(data['date'], data['value'], label='actual values')
plt.plot(future_dates_df['date'], future_dates_df['predicted_value'], label='predicted values')
plt.legend()
plt.show()
