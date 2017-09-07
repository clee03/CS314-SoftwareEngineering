import React, {Component} from 'react';
import Dropzone from 'react-dropzone'

class Home extends React.Component {
  render() {
    let total = this.calculateTotalDistance(); //update the total here
      return <div className="home-container">
        <div className="inner">
          <div id="header">t17 - TBD</div>
            <Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
              <b id="title">Itenerary:</b>
              <button>Open JSON File</button>
            </Dropzone>
              <table className="pair-table">
                {this.props.pairs}
                  <tbody>
                    <tr>
                      <td colSpan="2">Total:</td>
                      <td>{total}</td>
                    </tr>
                  </tbody>
              </table>
          </div>
      </div>
    }

    // calculate the total distance for the trip
    calculateTotalDistance(){
      let totalDistance = 0;
      for (let i = 0; i < this.props.pairs.length; ++i){
        totalDistance += this.props.pairs[i].props.dist;
        //++totalDistance;
      }
      return totalDistance;
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