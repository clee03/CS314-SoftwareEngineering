package edu.csu2017fa314.T17.Model;

import java.util.ArrayList;

public class ShorterTrip {
  protected int[][] mileageTable;
  protected ArrayList<Brewery> dests;
  protected type trip;
  protected int[] distances;

  public enum type{NearestNeighbor, TwoOpt}

  public ShorterTrip(){
  }

  public ShorterTrip(ArrayList<Brewery> destList, type trip) {
    this.dests = destList;
    this.mileageTable = new int[dests.size()][dests.size()];
    mileageTable = populateMileageTable();
    this.trip = trip;
    this.distances = new int[dests.size()];
  }

  public ShorterTrip(ArrayList<Brewery> destList) {
    this(destList, type.NearestNeighbor);
  }

  public ArrayList<Brewery> computePath() {
    ArrayList<Brewery> pathList = new ArrayList<>(); //return list
    int min = Integer.MAX_VALUE; //initial value for minimum distance
    int startNode = 0;
    Thread threads[] = new Thread[dests.size()];
    //generate and start all threads
    for(int i = 0; i < threads.length; i++){
      threads[i] = new Thread(new PST(this, i));
      threads[i].start();
    }
    //wait for threads to finish
    for(int i = 0; i < threads.length; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    //find the shortest trip
    for(int i = 0; i < threads.length; i++){
      if (min > distances[i]) {
        min = distances[i];
        startNode = i;
      }
    }
    //rebuild the shortest trip
    int[] pathFromA = pathFromAlpha(startNode);
    for (int i = 0; i < pathFromA.length; i++) {
      pathList.add(dests.get(pathFromA[i]));
    }
    return pathList;
  }

  public int[] pathFromAlpha(int startNode) { //nearest neighbor
    ArrayList<Integer> validNodes = new ArrayList<>(dests.size());
    int path[] = new int[dests.size()+1];
    int pathIndex = 0;
    int superStart = startNode;
    for (int i = 0; i < dests.size(); i++) {
      validNodes.add(i);
    }
    validNodes.remove(validNodes.indexOf(superStart)); //swap index with a location
    path[pathIndex++] = superStart; // can be an array
    while (!validNodes.isEmpty()) {
      startNode = minIndex(startNode, validNodes);
      path[pathIndex++] = startNode; //store at index i
    }
    path[pathIndex++] = superStart;
    if(this.trip == type.TwoOpt){
      twoOpt(path);
    }
    return path;
  }

  public int minIndex(int startNode, ArrayList<Integer> validNodes) {
    int min = Integer.MAX_VALUE;
    int location = 0;
    for (int j = 0; j < mileageTable[startNode].length; j++) {
      if ((min > mileageTable[startNode][j]) && (validNodes.contains(j)) && (mileageTable[startNode][j] >= 0)) {
        min = mileageTable[startNode][j];
        location = j;
      }
    }
    if (validNodes.indexOf(location) > -1) {
      validNodes.remove(validNodes.indexOf(location));
    }
    return location;
  }

  public int[][] populateMileageTable() {
    Distance dist = new Distance();
    for (int i = 0; i < mileageTable.length; i++) {
      for (int j = 0; j < mileageTable[i].length; j++) {
        mileageTable[i][j] = dist.greatCircleDistance(dests.get(i), dests.get(j));
      }
    }
    return mileageTable;
  }

  public int pathDistance(int[] path) {
    int ret = 0;
    for (int i = 0; i < path.length-1; i++) {
      ret += mileageTable[path[i]][path[i+1]];
    }
    return ret;
  }

  public int pathDistanceBrews(ArrayList<Brewery> path) {
    Distance dist = new Distance();
    int ret = 0;
    for (int i = 0; i < path.size()-1; i++) {
      ret += dist.greatCircleDistance(path.get(i), path.get(i+1));
    }
    return ret;
  }
  public int[] twoOptSwap(int[] path, int  i, int k) { // swap in place
    int temp;
    while(i < k) {
      temp = path[i];
      path[i]= path[k];
      path[k] = temp;
      i++; k--;
    }
    return path;
  }
  public int[] twoOpt(int[] path) {
    boolean improvement = true;
    int delta;
    int n = path.length;
    while (improvement) {
      improvement = false;
      for (int i = 0; i <= n - 3; i++) { // check n>4
        for (int k = i + 2; k < n - 1; k++) {// This has to be < not <= or we get an out of bounds error because we test k+1
          delta = -mileageTable[path[i]][path[i+1]] - mileageTable[path[k]][path[k+1]]
              + mileageTable[path[i]][path[k]] + mileageTable[path[i+1]][path[k+1]];
          if (delta < 0) { //improvement?
            path = twoOptSwap(path, i+1, k);
            improvement = true;
          }
        }
      }
    }
    return path;
  }
}
//helper class so i make runnable methods for shorter trip
class PST extends ShorterTrip implements Runnable{
  private int startNode;
  private ShorterTrip st;
  public PST(ShorterTrip st, int startNode){
    this.startNode = startNode;
    this.st = st;
  }

  @Override
  public void run() { //the method that actually runs
    st.distances[startNode] = st.pathDistance(st.pathFromAlpha(this.startNode));
  }
}