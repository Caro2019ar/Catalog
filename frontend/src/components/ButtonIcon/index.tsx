import './styles.css';
import { ReactComponent as Arrow } from 'assets/images/arrow.svg';

const ButtonIcon = () => {
  return (
    <div className="btn-container">
      <button className="btn btn-primary">
        <h6>Start searching  now</h6>
      </button>
      <div className="btn-icon-container">
        <Arrow />
      </div>
    </div>
  );
};

export default ButtonIcon;
