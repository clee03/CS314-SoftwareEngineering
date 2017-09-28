import React, {Component} from 'react';
import Dropzone from 'react-dropzone';
import ReactDOM from 'react-dom';
import ReactSVG from 'react-svg';

class Home extends React.Component {
  render() {
    let total = this.getTotalDistance();
      return <span className="home-container">
        <span className="inner">
          <span id="header">t17 - TBD</span>
            <Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
              <b id="title">Itenerary:</b>
              <button>Open JSON File</button>
            </Dropzone>
            <Dropzone className="dropzone-style" onDrop={this.displayVector.bind(this)}>
              <b id="title">Map SVG:</b>
              <button>Open SVG File</button>
            </Dropzone>
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
        </span>
        <span id='root'/>
      </span>
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
        ReactDOM.render(<ReactSVG id='map'
          style={{"border-style":"solid", "margin-top":"100px", "margin-left":"1%"}}
          path={file.name} />, document.getElementById('root'));
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
