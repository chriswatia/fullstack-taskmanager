import axios from "axios";

const API_URL = "http://localhost:8080/api/v1";

export const getProjects = () => {
    return axios.get(API_URL);
  };
  
  export const createProject = (project) => {
    return axios.post(API_URL, project);
  };
  
  export const getTasks = (projectId) => {
    return axios.get(`${API_URL}/${projectId}`);
  };
  
  export const createTask = (projectId, task) => {
    return axios.post(`${API_URL}/${projectId}/tasks`, task);
  };