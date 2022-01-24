import { ReactComponent as Arrow } from 'assets/images/arrow.svg';
import ProductPrice from 'components/ProductPrice';
import './styles.css';
const ProductDetails = () => {
  return (
    <div className="product-details-container">
      <div className="base-card product-details-card">
        <div className="goback-container">
          <Arrow />
          <h2>Back</h2>
        </div>
        <div className="row">
          <div className="col-xl-6">
            <div className="img-container">
              <img
                src="https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg"
                alt="TV"
              />
            </div>
            <div className="name-price-container">
              <h1>Nome do produto</h1>
              <ProductPrice price={2190.0} />
            </div>
          </div>
          <div className="col-xl-6">
            <div className="description-container">
              <h2>Description of product</h2>
              <p>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Sed
                deleniti nam veniam, voluptatibus minus pariatur accusamus,
                aliquid placeat aliquam labore repudiandae? Qui, nulla debitis
                soluta assumenda dignissimos suscipit vitae quia alias culpa
                doloremque, sequi consectetur aliquam tempore? Quaerat, saepe
                fugit! Voluptates expedita aliquam dolor vel tempore cum iure,
                asperiores delectus!
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductDetails;
