import React, { useEffect } from 'react'
import { Link, useParams } from 'react-router-dom'
import { useState } from 'react'
import axios from 'axios'

const ViewSuperhero = () => {

    const [superhero, setSuperhero]=useState({
        name: '',
        nickname: '',
        superpowers: [],  // This is an array with one element
        telephoneNumber: '',
        dateOfBirth: '1997-07-07',
        experiencePoints: 0,
        hasSecretIdentity: false,
        superheroRevenue: 0.00,
    })

    const {id}=useParams();

    useEffect(()=>{
        loadSuperhero();
    }, [])

    const loadSuperhero=async ()=> {
        const result=await axios.get(`http://localhost:8080/superheroes/${id}`);
        setSuperhero(result.data)
    }

  return (
    <div className="container">
    <div className="row">
      <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
        <h2 className="text-center m-4">Superhero Details</h2>
        <div className='card'>
            <div className='card-header'>
                Superhero code: {superhero.id}
                <ul className='list-group list-group-flush'>
                    <li className='list-group-item'>
                        <b>Name: </b>
                        {superhero.name}
                    </li>
                    <li className='list-group-item'>
                        <b>Nickname: </b>
                        {superhero.nickname}
                    </li>
                    <li className='list-group-item'>
                        <b>Superpowers: </b>
                        {superhero.superpowers.map(superpower => superpower.superpowerName).join(", ")}
                    </li>
                    <li className='list-group-item'>
                        <b>Telephone Number: </b>
                        {superhero.telephoneNumber}
                    </li>
                    <li className='list-group-item'>
                        <b>DOB: </b>
                        {superhero.dateOfBirth}
                    </li>
                    <li className='list-group-item'>
                        <b>XP: </b>
                        {superhero.experiencePoints}
                    </li>
                    <li className='list-group-item'>
                        <b>Secret Idenity: </b>
                        {superhero.hasSecretIdentity ? "true" : "false"}
                    </li>
                    <li className='list-group-item'>
                        <b>Superhero Revenue: </b>
                        {superhero.superheroRevenue}
                    </li>
                </ul>
            </div>

        </div>
        <Link className='btn btn-primary my-2' to={"/"}>Back to Home</Link>
        </div>
        </div>
        </div>
  )
}

export default ViewSuperhero;
