import React, { useState, useEffect } from "react";
import { getProjects, createProject, getTasks, createTask } from "./Service";

function App() {
  const [projects, setProjects] = useState([]);
  const [newProject, setNewProject] = useState({ name: "", description: "" });
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState({
    title: "",
    description: "",
    status: "TO_DO",
    dueDate: "",
  });
  const [currentProjectId, setCurrentProjectId] = useState(null); // Track the currently selected project

  // Fetch all projects on page load
  useEffect(() => {
    getProjects()
      .then((response) => setProjects(response.data))
      .catch((error) => console.error("Error fetching projects:", error));
  }, []);

  // Create a new project
  const handleProjectCreate = () => {
    createProject(newProject)
      .then((response) => {
        setProjects([...projects, response.data]);
        setNewProject({ name: "", description: "" });
      })
      .catch((error) => console.error("Error creating project:", error));
  };

  // Fetch tasks for a specific project
  const fetchTasks = (projectId) => {
    getTasks(projectId)
      .then((response) => {
        setTasks(response.data.tasks);
        setCurrentProjectId(projectId); // Set the current project ID
      })
      .catch((error) => console.error("Error fetching tasks:", error));
  };

  // Add a new task to a project
  const handleTaskCreate = () => {
    createTask(currentProjectId, newTask)
      .then((response) => {
        setTasks([...tasks, response.data]);
        setNewTask({
          title: "",
          description: "",
          status: "TO_DO",
          dueDate: "",
        });
      })
      .catch((error) => console.error("Error creating task:", error));
  };

  return (
    <div className="container mt-5">
      <h1>Project and Task Management</h1>

      {/* Project Creation */}
      <div className="card mb-4">
        <div className="card-body">
          <h5 className="card-title">Create Project</h5>
          <form>
            <div className="row">
              <div className="col-md-6">
                <div className="form-group">
                  <label>Project Name</label>
                  <input
                    type="text"
                    className="form-control"
                    value={newProject.name}
                    onChange={(e) =>
                      setNewProject({ ...newProject, name: e.target.value })
                    }
                    required
                  />
                </div>
              </div>
              <div className="col-md-6">
                <div className="form-group">
                  <label>Description</label>
                  <textarea
                    className="form-control"
                    value={newProject.description}
                    onChange={(e) =>
                      setNewProject({
                        ...newProject,
                        description: e.target.value,
                      })
                    }
                    required
                  ></textarea>
                </div>
              </div>
              <button
                type="button"
                className="btn btn-primary mt-3"
                onClick={handleProjectCreate}
              >
                Create Project
              </button>
            </div>
          </form>
        </div>
      </div>

      {/* List of Projects */}
      <div className="row">
        {projects.map((project) => (
          <div className="col-md-4" key={project.id}>
            <div className="card">
              <div className="card-body">
                <h5 className="card-title">{project.name}</h5>
                <p className="card-text">{project.description}</p>
                <button
                  className="btn btn-info"
                  onClick={() => fetchTasks(project.id)}
                >
                  View Tasks
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>

      {/* Task Creation */}
      {currentProjectId && (
        <div className="card mt-4">
          <div className="card-body">
            <h5 className="card-title">Add Task to Project</h5>
            <form>
              <div className="row">
                <div className="col-md-6">
                  <div className="form-group">
                    <label>Task Title</label>
                    <input
                      type="text"
                      className="form-control"
                      value={newTask.title}
                      onChange={(e) =>
                        setNewTask({ ...newTask, title: e.target.value })
                      }
                      required
                    />
                  </div>
                </div>
                <div className="col-md-6">
                  <div className="form-group">
                    <label>Description</label>
                    <textarea
                      className="form-control"
                      value={newTask.description}
                      onChange={(e) =>
                        setNewTask({ ...newTask, description: e.target.value })
                      }
                      required
                    ></textarea>
                  </div>
                </div>
              </div>

              <div className="row">
                <div className="col-md-6">
                  <div className="form-group">
                    <label>Status</label>
                    <select
                      className="form-control"
                      value={newTask.status}
                      onChange={(e) =>
                        setNewTask({ ...newTask, status: e.target.value })
                      }
                    >
                      <option value="TO_DO">TO DO</option>
                      <option value="IN_PROGRESS">IN PROGRESS</option>
                      <option value="DONE">DONE</option>
                    </select>
                  </div>
                </div>
                <div className="col-md-6">
                  <div className="form-group">
                    <label>Due Date</label>
                    <input
                      type="date"
                      className="form-control"
                      value={newTask.dueDate}
                      onChange={(e) =>
                        setNewTask({ ...newTask, dueDate: e.target.value })
                      }
                    />
                  </div>
                </div>
              </div>

              <div>
                <button
                  type="button"
                  className="btn btn-primary mt-3"
                  onClick={handleTaskCreate}
                >
                  Add Task
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* List of Tasks */}
      {tasks.length > 0 && (
        <div className="mt-4">
          <h3>Tasks</h3>
          <table className="table table-bordered">
            <thead>
              <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Status</th>
                <th>Due Date</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {tasks.map((task) => (
                <tr key={task.id}>
                  <td>{task.title}</td>
                  <td>{task.description}</td>
                  <td>{task.status}</td>
                  <td>{task.dueDate}</td>
                  <td>
                    <button className="btn btn-warning">Edit</button>
                    <button className="btn btn-danger ml-2">Delete</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default App;
