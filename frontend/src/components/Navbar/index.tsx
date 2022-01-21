import './styles.css';
import 'bootstrap/js/src/collapse.js';
import { Link, NavLink } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-md navbar-dark bg-primary main-nav">
      <div className="container-fluid">
        <Link to="/" className="nav-logo-text">
          <h4>MyCatalog</h4>
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#catalog-navbar"
          aria-controls="catalog-navbar"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="catalog-navbar">
          <ul className="navbar-nav offset-md-2 main-menu">
            <li>
              <NavLink
                to="/"
                className={(isActive) =>
                  'nav-link' + (!isActive ? ' unselected' : '')
                }
              >
                HOME
              </NavLink>
            </li>
            <li>
              <NavLink
                to="/products"
                className={(isActive) =>
                  'nav-link' + (!isActive ? ' unselected' : '')
                }
              >
                CATALOG
              </NavLink>
            </li>
            <li>
              <NavLink
                to="/admin"
                className={(isActive) =>
                  'nav-link' + (!isActive ? ' unselected' : '')
                }
              >
                ADMIN
              </NavLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
