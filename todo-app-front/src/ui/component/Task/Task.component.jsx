import React from 'react'
import { TaskApi } from '../../../hooks/task/task.hook'
import './style.css'

export function Task({ task, update }) {

    const taskApi = TaskApi()

    function capitalizeFirstLetter(word) {
        const string = word.toLowerCase()
        return string.charAt(0).toUpperCase() + string.slice(1)
    }

    async function handleDeleteTask() {
        try {
            if (window.confirm("Are you sure?")) {
                await taskApi.deleteTask(task.id)
                update()
            }
        } catch (err) {
            window.alert("An unexpected error occurred. Try again later!")
        }
    }

    async function handleFinishTask() {
        try {
            await taskApi.finishTask(task.id)
            update()
        } catch (err) {
            window.alert("An unexpected error occurred. Try again later!")
        }
    }

    return (
        <div className="task-container">
            <header>
                <h2 className={task.priority === 'URGENT' ? 'urgent' : undefined}>
                    {capitalizeFirstLetter(task.priority)}
                </h2>
            </header>
            <main>
                <p className={task.hasFinished ? 'line-through' : undefined}>
                    <strong>Description: </strong>{task.description}
                </p>
                <p className={task.hasFinished ? 'line-through' : undefined}>
                    <strong>Prevision: </strong>{`${task.conclusionPrevision[0]}/${task.conclusionPrevision[1]}/${task.conclusionPrevision[2]}`}
                </p>
            </main>
            <footer>
                {!task.hasFinished && <button className="check-img" onClick={handleFinishTask}></button>}
                <button className="remove-img" onClick={handleDeleteTask}></button>
            </footer>
        </div>
    )
}