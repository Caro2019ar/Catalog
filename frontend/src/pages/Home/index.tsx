import './styles.css';
import { ReactComponent as MainImage } from 'assets/images/main-image.svg';
import { Link } from 'react-router-dom';
import ButtonIcon from 'components/ButtonIcon';

const Home = () => {
  return (
    <div className="home-container">
      <div className="base-card home-card">
        <div className="home-content-container">
          <div>
            <h1>Find the best catalog of products</h1>
            <p>We will help you find the best products in the market</p>
          </div>
          <div>
            <Link to="/products">
              <ButtonIcon />
            </Link>
          </div>
        </div>

        <div className="home-image-container">
          <MainImage />
        </div>
      </div>
    </div>
  );
};

export default Home;
