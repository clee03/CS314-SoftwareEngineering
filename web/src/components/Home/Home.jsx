import React, {Component} from 'react';
import Dropzone from 'react-dropzone';
import Itinerary from '../Itinerary.jsx';


class Home extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    console.log(this.props.dataIter);
      return (
        <span className="home-container">
          <span>
            <span id="header">t17 - TBD</span>
          </span>
          <span>
          <Itinerary
            style= {{width:'50%'}}
            dataIter= {this.props.dataIter}
          />
          <span style={{display:'inline',width:'50%'}}>
            <span dangerouslySetInnerHTML={{__html: this.props.dataSvg}}/>
          </span>
          </span>
        </span>
      );
    }
}


export default Home
