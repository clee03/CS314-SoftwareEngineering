import React, {Component} from 'react';
import Dropzone from 'react-dropzone';
import Itinerary from '../Itinerary.jsx';
import SVGMap from '../SVGMap.jsx';

class Home extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      datafile: [],
      imgfile: []
    };
  }

  render() {
      return <span className="home-container">
        <span className="inner">
          <span id="header">t17 - TBD</span>
            <Dropzone className="dropzone-style" onDrop={this.dataDrop.bind(this)}>
              <b id="title">Itenerary:</b>
              <button>Open JSON File</button>
            </Dropzone>
            <Dropzone className="dropzone-style" onDrop={this.imgDrop.bind(this)}>
              <b id="title">Map SVG:</b>
              <button>Open SVG File</button>
            </Dropzone>
            <Itinerary
              file= {this.state.datafile}
            />
            <SVGMap
              file= {this.state.imgfile}
            />
        </span>
      </span>
    }

    // grab the total distance stored in the last element if pairs isn't empty /
    imgDrop(acceptedFiles){
      console.log("Accepting drop");
      acceptedFiles.forEach(file => {
        console.log("Filename:", file.name, "File:", file);

        const fr = new FileReader();
        fr.onload = function(e) {
            document.getElementById('map').src = e.target.result;
        };
        fr.readAsDataURL(file);
      });
    }

    captureHeaders (jsonObj) {
      console.log("Capturing headers: ");
      const headers = Object.keys(jsonObj[0]['start']);
      let options = [];
      for (var i in headers ) {
        options.push( { 'label': headers[i], 'value': headers[i] } );
      }
      this.state.options = options;

      console.log( options );
      console.log("Done");
    }

    dataDrop(acceptedFiles) {
      console.log("Accepting drop");
      acceptedFiles.forEach(file => {
        console.log("Filename:", file.name, "File:", file);
        console.log(JSON.stringify(file));
        this.setState({datafile: file});

        let fr = new FileReader();
        fr.onload = (function () {
          return function (e) {
            let JsonObj = JSON.parse(e.target.result);
            this.captureHeaders(JsonObj);
            this.props.browseFile(JsonObj);
          };
        })(file).bind(this);
        fr.readAsText(file);
      });
    }
}


export default Home
