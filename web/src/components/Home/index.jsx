import React, {Component} from 'react';
import Dropzone from 'react-dropzone';
import Itinerary from '../Itinerary/index.jsx';
import LocationMap from '../LocationMap/index.jsx';


class Home extends React.Component {
  constructor(props) {
    super(props);
    this.extractCoords = this.extractCoords.bind(this);
  }

  extractCoords() {
    let data = this.props.dataIter;
    if(data.length === 0)
      return;
    let coords = data.map(
      x =>
        ({ lat: x.start.latitude,
          lng: x.start.longitude })
    );
    coords.push({lat: data[0].start.latitude, lng: data[0].start.longitude})
    return coords;
  }

  render() {
    const coords = this.extractCoords();
    return (
      <div className="home-container">
        <div>
          <LocationMap
            path={coords}
          />
          <Itinerary
            dataIter= {this.props.dataIter}
          />
        </div>
      </div>
    );
  }
}


export default Home
