import React, { useEffect, useState } from 'react'
import { TaskApi } from '../../../hooks/task/task.hook'
import { Task } from '../../component/Task/Task.component'
import './style.css'

export function TaskScreen() {
    const [taskList, setTaskList] = useState([])
    const [page, setPage] = useState(0)
    const [loading, setLoading] = useState(false)
    const taskApi = TaskApi()

    async function handleFindTasks() {
        try {
            setTaskList(await taskApi.findTasks(page))
            // setLoading(false)
        } catch (err) {
            console.log(err)
        }
    }
    
    useEffect(() => {
        handleFindTasks()
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [])

    return (
        <div className="tasks-container">
            {loading ?
                <h1>Loading</h1>
                :
                taskList.map((element, index) => <Task task={element} key={index} update={handleFindTasks}/>)
                
            }
        </div>
    )

}