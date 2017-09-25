import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import ReactSVG from 'react-svg';
import Dropzone from 'react-dropzone';

class Home extends React.Component {
  render() {
    let total = this.getTotalDistance(); //update the total here
      return <div className="home-container">
        <div className="inner">
          <div id="header">t17 - TBD</div>
            <Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
              <b id="title">Itenerary:</b>
              <button>Open JSON File</button>
            </Dropzone>
            
            <Dropzone className="dropzone-style" onDrop={this.displayVector.bind(this)}>
              <b id="title">Colorado SVG:</b>
              <button>Open SVG File</button>
            </Dropzone>
            <Dropzone className="dropzone-style" onDrop={this.displayVector.bind(this)}>
              <b id="title">Itenerary SVG:</b>
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
              
             
          </div>
      </div>
    }

    // grab the total distance stored in the last element if pairs isn't empty /
    getTotalDistance(){
      if(this.props.pairs.length == 0) return 0;
      return this.props.pairs[this.props.pairs.length - 1].props.totalDist;
    }

    //display svg method
    displayVector(acceptedFiles) {
        console.log("Accepting File");
            //figure out svg code here
        acceptedFiles.forEach(file => {
            console.log("Filename:", file.name, "File:", file);
            ReactDOM.render( <ReactSVG path={file.name} style={{height:800}}/>, document.getElementById('root') )
        })
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
