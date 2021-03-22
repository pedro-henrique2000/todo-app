import axios from 'axios'

const httpClient = axios.create({
    baseURL: 'http://localhost:8080'
})

export function TaskApi() {
    const { token } = JSON.parse(localStorage.getItem('user'))

    async function findTasks(page) {
        const response = await httpClient.get(`/tasks/${page}`, {
            headers: {
                'Authorization': token
            }
        })
        return response.data
    }

    async function deleteTask(id) {
        const response = await httpClient.delete(`/task/${id}`, {
            headers: {
                'Authorization': token
            }
        })
        return response.data
    }

    async function finishTask(id) {
        const response = await httpClient.post(`/task/${id}/finish`, {}, {
            headers: {
                'Authorization': token
            }
        })
        return response.data
    }

    async function createTask(description, conclusionPrevision, priority) {
        const response = await httpClient.post(`/task/create`, { description, conclusionPrevision, priority }, {
            headers: {
                'Authorization': token
            }
        })
        return response.data
    }

    return {
        findTasks, deleteTask, finishTask, createTask
    }

}