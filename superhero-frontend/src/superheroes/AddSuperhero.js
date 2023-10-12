import React from "react";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const AddSuperhero = () => {
  let navigate = useNavigate();

  const [superhero, setSuperhero] = useState({
    name: "",
    nickname: "",
    superpowers: [{ superpowerName: "" }], // Array of objects with a "superpowerName" property
    telephoneNumber: "",
    dateOfBirth: "1997-07-07",
    experiencePoints: 0,
    hasSecretIdentity: false,
    superheroRevenue: 0.0,
  });

  const {
    name,
    nickname,
    superpowers,
    telephoneNumber,
    dateOfBirth,
    experiencePoints,
    hasSecretIdentity,
    superheroRevenue,
  } = superhero;

  const onInputChange = (e) => {
    const { name, value, type, checked } = e.target;
  
    // If it's a checkbox, set the value to a boolean
    const newValue = type === "checkbox" ? checked : name === "superpowers"
      ? value
          .split(",")
          .map((superpowerName) => ({
            superpowerName: superpowerName.trim(),
          }))
      : value;
  
    setSuperhero({ ...superhero, [name]: newValue });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    console.log("Sending request with data:", superhero);
    try {
      const response = await axios.post(
        "http://localhost:8080/superheroes",
        superhero,
        {
          headers: {         
            "Content-Type": "application/json",
          },
        }
      );
      console.log("Server response:", response.data);
      navigate("/");
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Register Superhero</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
                Name
              </label>
              <input
                required
                type={"text"}
                className="form-control"
                placeholder="Enter name"
                name="name"
                value={name}
                onChange={(e) => onInputChange(e)
                }
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Nickname" className="form-label">
                Nickname
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter nickname"
                name="nickname"
                value={nickname}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Superpowers" className="form-label">
                Superpowers
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter superpowers (comma-separated)"
                name="superpowers"
                value={superpowers
                  .map((superpower) => superpower.superpowerName)
                  .join(", ")}
                onChange={(e) => onInputChange(e)}
              />
            </div>

            <div className="mb-3">
              <label htmlFor="telephoneNumber" className="form-label">
                Telephone Number
              </label>
              <input
                type="tel"
                className="form-control"
                placeholder="000-000-0000"
                name="telephoneNumber"
                value={telephoneNumber}
                onChange={(e) => onInputChange(e)}
                pattern="^\d{3}-\d{3}-\d{4}$"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="dateOfBirth" className="form-label">
                Date Of Birth
              </label>
              <input
                type="date"
                className="form-control"
                placeholder="Enter date of birth"
                name="dateOfBirth"
                value={dateOfBirth}
                onChange={(e) => onInputChange(e)}
              />
            </div>

            <div className="mb-3">
              <label htmlFor="experiencePoints" className="form-label">
                Experience Points
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter experience points"
                name="experiencePoints"
                value={experiencePoints}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="hasSecretIdentity" className="form-label">
                Secret identity?
              </label>
              <input
                type="checkbox"
                className="form-check-input"
                name="hasSecretIdentity"
                checked={hasSecretIdentity}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="superheroRevenue" className="form-label">
                Superhero Revenue
              </label>
              <input
                type={"number"}
                className="form-control"
                placeholder="Enter superhero revenue"
                name="superheroRevenue"
                value={superheroRevenue}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <button type="submit" className="btn btn-outline-danger mx-2">
              Cancel
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default AddSuperhero;
