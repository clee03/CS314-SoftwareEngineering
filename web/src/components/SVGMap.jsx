import React, {Component} from 'react';

class SVGMap extends React.Component{
  constructor(props) {
    super(props);
  }

  render() {
    return (
        <img
          style= {this.props.style}
          src={this.props.imgsrc}
          id='map'
        />
    );
  }
}

export default SVGMap