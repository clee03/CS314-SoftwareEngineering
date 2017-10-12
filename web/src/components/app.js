import React from 'react';
import Home from './Home/Home.jsx';

export default class App extends React.Component {
  constructor(props) {
    super(props);
  };

  render() {
    return (
      <div className="app-container">
        <Home
        />
      </div>
    )
  }
}
