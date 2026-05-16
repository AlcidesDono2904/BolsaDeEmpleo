import { useState,  } from 'react'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'

import Login from './pages/Login'
import Home from "./pages/Home.jsx";


function App() {
  const [count, setCount] = useState(0)

  return (
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<Home/>}/>
              <Route path="/login" element={<Login/>}/>

          </Routes>
      </BrowserRouter>
  )
}

export default App
