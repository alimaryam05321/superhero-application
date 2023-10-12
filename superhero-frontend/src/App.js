import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import NavBar from './layout/NavBar';
import Home from './pages/Home';
import AddSuperhero from './superheroes/AddSuperhero';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import EditSuperhero from './superheroes/EditSuperhero';
import ViewSuperhero from './superheroes/ViewSuperhero';

const App = () => {
  return (
    <div className="App">
      <Router>
      <NavBar/>
      <Routes>
        <Route exact path="/" element={<Home/>} />
        <Route exact path="/addsuperhero" element={<AddSuperhero/>} />
        <Route exact path="/editsuperhero/:id" element={<EditSuperhero/>} />
        <Route exact path="/viewsuperhero/:id" element={<ViewSuperhero/>} />       
      </Routes>
      </Router>
    </div>
  );
}

export default App;
