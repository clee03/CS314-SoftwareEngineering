import React, {Component} from 'react';
import Dropzone from 'react-dropzone';
import Itinerary from '../Itinerary/index.jsx';


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
              dataIter= {this.props.dataIter}
            />
          </div>
        </div>
      );
    }
}


export default Home
