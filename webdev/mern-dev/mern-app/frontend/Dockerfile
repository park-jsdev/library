# Use an official Node.js runtime as a parent image
FROM node:14

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the frontend
RUN npm run build

# Install serve to serve the build
RUN npm install -g serve

# Expose the frontend port
EXPOSE 3000

# Start the frontend server
CMD ["serve", "-s", "build"]
