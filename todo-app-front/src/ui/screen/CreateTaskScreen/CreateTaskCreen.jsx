import React, { useState } from 'react'
import { TaskApi } from '../../../hooks/task/task.hook'
import { Input } from '../../component/Input/Input.component'
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import moment from 'moment';

export function CreateTaskScreen() {
    const [description, setDescription] = useState('')
    const [prevision, setPrevision] = useState('')
    const [priority, setPriority] = useState('MODERATE')
    const [mensagem, setMensagem] = useState('')

    const taskApi = TaskApi()

    async function handleSubmit(event) {
        event.preventDefault()

        try {
            await taskApi.createTask(description, moment(prevision).format("YYYY/MM/DD"), priority)
            setMensagem("Task created!")
        } catch (err) {
            setMensagem(err.response.data.message)
        }
    }

    function handleSelectChange(e) {
        setPriority(e.target.value)
    }

    return (
        <div className="form-container">
            <header>
                <h2>Create a Task</h2>
            </header>
            <main>
                <form onSubmit={handleSubmit}>
                    <Input
                        type="text"
                        name="Description"
                        label="Description"
                        handleChange={setDescription}
                        value={description}
                    />

                    <DatePicker selected={prevision} onChange={date => setPrevision(date)} dateFormat="yyyy-MM-dd" />

                    <label htmlFor="priority">
                        <select name="priotity" id="priotity" onChange={handleSelectChange}>
                            <option value="MODERATE">Moderate</option>
                            <option value="URGENT">Urgent</option>
                        </select>
                    </label>
                    {mensagem ? <span>{mensagem}</span> : ''}
                    <button className="button">Register</button>
                </form>
            </main>
        </div>

    )
}