import React, { useEffect, useState } from 'react'
import { TaskApi } from '../../../hooks/task/task.hook'
import { Task } from '../../component/Task/Task.component'
import './style.css'

export function TaskScreen() {
    const [taskList, setTaskList] = useState([])
    const [page, setPage] = useState(0)
    const [loading, setLoading] = useState(true)
    const taskApi = TaskApi()

    async function handleFindTasks() {
        try {
            setTaskList(await taskApi.findTasks(page))
            setLoading(false)
        } catch (err) {
            console.log(err)
        }
    }
    
    useEffect(() => {
        handleFindTasks()
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [page])

    function handleNextpage() {
        setLoading(true)
        setPage(page + 1)
    }

    function handlePreviousPage() {
        setLoading(true)
        setPage(page - 1)
    }

    return (
        <div className="tasks-container">
        <div className="task-list">
            {loading ?
                <img src="loading.gif" width="200px" height="200px" alt="Loading"/>
                :
                taskList.map((element, index) => <Task task={element} key={index} update={handleFindTasks}/>)
            }
        </div>
            <div className="task-pagination">
                <button disabled={page === 0} onClick={handlePreviousPage}>Previous Page</button>
                <button disabled={taskList.length === 0} onClick={handleNextpage}>Next Page</button>
            </div>
        </div>
    )

}