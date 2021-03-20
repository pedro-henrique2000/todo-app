import React from 'react'
import './style.css'

export function Input({name, handleChange, value, type}) {
    function change(event) {
        handleChange(event.target.value)
    }

    return(
        <div className="label-container">
            <label className="label">
                <input 
                    value={value}
                    type={type}
                    name={name}
                    onChange={change}
                    className='input'
                    required
                />
                <span className="placeholder">{name}</span>
            </label>
        </div>
    )
}