import React, {Component} from 'react';
import Dropzone from 'react-dropzone';

const half = {
  width: "50%"
};

class Home extends React.Component {
  render() {
      return <span className="home-container">
        <span style={half} className="inner">
          <span id="header">t17 - TBD</span>
            <Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
              <b id="title">Itenerary:</b>
              <button>Open JSON File</button>
            </Dropzone>
            <Dropzone className="dropzone-style" onDrop={this.displayVector.bind(this)}>
              <b id="title">Map SVG:</b>
              <button>Open SVG File</button>
            </Dropzone>
            {this.renderTable()}
        </span>
        <img style={half} id='map'/>
      </span>
    }

    renderTable() {
      let total = this.getTotalDistance();
      return (
        <table className="pair-table">
          <tr>
            <td>Start Brewery</td>
            <td>End Brewery</td>
            <td>Distance</td>
            <td>Total Distance</td>
          </tr>
          {this.props.pairs}
          <tbody>
            <tr>
              <td colSpan="3">Trip Total:</td>
              <td>{total}</td>
            </tr>
          </tbody>
        </table>
      );
    }

    // grab the total distance stored in the last element if pairs isn't empty /
    getTotalDistance(){
      if(this.props.pairs.length == 0) return 0;
      return this.props.pairs[this.props.pairs.length - 1].props.totalDist;
    }
    displayVector(acceptedFiles){
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
    drop(acceptedFiles) {
      console.log("Accepting drop");
      acceptedFiles.forEach(file => {
        console.log("Filename:", file.name, "File:", file);
        console.log(JSON.stringify(file));
        let fr = new FileReader();
        fr.onload = (function () {
          return function (e) {
            let JsonObj = JSON.parse(e.target.result);
            console.log(JsonObj);
            this.props.browseFile(JsonObj);
          };
        })(file).bind(this);
        fr.readAsText(file);
      });
    }
}

export default Home
