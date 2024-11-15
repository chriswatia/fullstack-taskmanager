# Step 1: Build the React app
FROM node:16 AS build

# Set the working directory
WORKDIR /app

# Install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the React app
RUN npm run build

# Step 2: Serve the React app with a lightweight web server
FROM nginx:alpine

# Copy the build output to Nginx's default public directory
COPY --from=build /app/build /usr/share/nginx/html

# Expose the port that the container will listen on
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
