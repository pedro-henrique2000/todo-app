import React, { useEffect, useState } from 'react'
import { HelloApi } from '../../../hooks/hello/hello.hook'

export function HelloScreen() {
    const [response, setResponse] = useState([])
    const helloApi = HelloApi()

    useEffect(() => {
        handleHello()
    }, [])

    const handleHello = async () => {
        const responseApi = await helloApi.hello()
        setResponse(responseApi.data.msg)
        console.log(responseApi)
    }


    return (
        <h1>{response}</h1>
    )
}