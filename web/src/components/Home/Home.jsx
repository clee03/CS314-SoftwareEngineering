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
        <div className="home-container">
          <div>
            <Itinerary
              style= {{width:'50%',marginBottom:'40px'}}
              dataIter= {this.props.dataIter}
            />
          </div>
          <div dangerouslySetInnerHTML={{__html: this.props.dataSvg}}/>
        </div>
      );
    }
}


export default Home
