import React, {Component} from 'react'
import {compose, withProps} from 'recompose'
import {withScriptjs, withGoogleMap,
        GoogleMap, Polyline, Marker} from 'react-google-maps'

import './index.scss'

class InnerMap extends React.Component {
  constructor(props) {
    super(props);
    this.makeMarkers = this.makeMarkers.bind(this);
  }

  makeMarkers() {
    let markers = this.props.path.map(
      x => <Marker position={{lat: x.lat, lng: x.lng}}/>
    );
    return markers;
  }

  render() {
    return (
      <GoogleMap
        defaultCenter={{lat: 0, lng: 0}}
        defaultZoom={1}
      >
        <Polyline path={this.props.path}
          options={{strokeColor: 'DeepSkyBlue'}}
        />
        {this.makeMarkers()}
      </GoogleMap>
    );
  }
}

const WrappedMap = compose(
  withProps({
    googleMapURL: 'https://maps.googleapis.com/maps/api/js?key=AIzaSyC44m7b-fRQF3KaecZ3NhW1rZGHik6KQCg' +
                  '&v=3.exp' +
                  '&libraries=geometry,drawing,places',
    loadingElement: <div style={{ height: `100%` }} />,
    containerElement: <div/>,
    mapElement: <div style={{ height: `100%` }} />
  }),
  withScriptjs,
  withGoogleMap
)(InnerMap);

class LocationMap extends React.Component {
  constructor(props){
    super(props);
  }

  render(){
    return (
      <div className='LocationMap'>
        <WrappedMap path={this.props.path} key='map'/>
      </div>
    )
  }
}

export default LocationMap