import React from 'react'
import { Link } from 'react-router-dom'
import './style.css'

export function DashboardScreen() {

    return (
        <div className="dashboard-container">
            <Link to="/task/create" className="create">Create a new Task</Link>
            <Link to="/tasks" className="tasks">My Tasks</Link>
        </div>
    )

}