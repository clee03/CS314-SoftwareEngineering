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
            <div dangerouslySetInnerHTML={{__html: this.props.dataSvg}}/>
            <Itinerary
              style= {{width:'50%',marginLeft:'30px', marginTop:'30px'}}
              dataIter= {this.props.dataIter}
            />
          </div>
        </div>
      );
    }
}


export default Home
