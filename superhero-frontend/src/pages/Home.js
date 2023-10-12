import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

const Home = () => {
  const [superheroes, setSuperheroes] = useState([]);

  const { id } = useParams();

  useEffect(() => {
    loadSuperheroes();
  }, []);

  const loadSuperheroes = async () => {
    const result = await axios.get("http://localhost:8080/superheroes");
    setSuperheroes(result.data);
  };

  const deleteSuperhero = async (id) => {
    await axios.delete(`http://localhost:8080/superheroes/${id}`);
    loadSuperheroes();
  };

  return (
    <div className="container">
      <div className="py-4"></div>
      <table className="table border shadow">
        <thead>
          <tr>
            <th scope="col">Superhero Code</th>
            <th scope="col">Name</th>
            <th scope="col">Nickname</th>
            <th scope="col">Superpowers</th>
            <th scope="col">Telephone Number</th>
            <th scope="col">Date of Birth</th>
            <th scope="col">Experience Points</th>
            <th scope="col">Secret Identity</th>
            <th scope="col">Superhero Revenue</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>
          {superheroes.map((superhero) => (
            <tr key={superhero.id}>
              <th scope="row">{superhero.id}</th>
              <td>{superhero.name}</td>
              <td>{superhero.nickname}</td>
              <td>
                {superhero.superpowers
                  .map((superpower) => superpower.superpowerName)
                  .join(", ")}
              </td>

              <td>{superhero.telephoneNumber}</td>
              <td>{superhero.dateOfBirth}</td>
              <td>{superhero.experiencePoints}</td>
              <td>{superhero.hasSecretIdentity.toString()}</td>
              <td>{superhero.superheroRevenue}</td>
              <td>
                <div
                  className="btn-group"
                  role="group"
                  aria-label="Superhero Actions"
                >
                  <Link
                    type="button"
                    className="btn btn-primary rounded mx-2"
                    to={`/viewsuperhero/${superhero.id}`}
                  >
                    View
                  </Link>
                  <Link
                    type="button"
                    className="btn btn-outline-primary rounded mx-2"
                    to={`/editsuperhero/${superhero.id}`}
                  >
                    Edit
                  </Link>
                  <button
                    type="button"
                    className="btn btn-danger rounded mx-2"
                    onClick={() => deleteSuperhero(superhero.id)}
                  >
                    Delete
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Home;
