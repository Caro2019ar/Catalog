import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from 'pages/Home';
import Catalog from 'pages/Catalog';
import Admin from 'pages/Admin';
import ProductDetails from 'pages/ProductDetails';
import Navbar from 'components/Navbar';

const RoutesRRD6 = () => (
  <BrowserRouter>
    <Navbar />
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/products" element={<Catalog />} />
      <Route path="/products/:productId" element={<ProductDetails />} />
      <Route path="/admin" element={<Admin />} />
    </Routes>
  </BrowserRouter>
);
export default RoutesRRD6;
