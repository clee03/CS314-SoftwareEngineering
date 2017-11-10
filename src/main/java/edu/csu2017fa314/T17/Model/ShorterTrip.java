package edu.csu2017fa314.T17.Model;

import java.util.ArrayList;

public class ShorterTrip {
  protected int[][] mileageTable;
  protected ArrayList<Brewery> dests;
  protected type trip;
  protected int[] distances;
  protected Distance distance;

  public enum type{NoOpt, NearestNeighbor, TwoOpt, ThreeOpt}

  /**
   *  default constructor
   */
  public ShorterTrip(){
  }

  /**
   *  a constructor for shorterTrip that assumes kilometers for distance
   *  @param destList   the base list of elements to be reordered
   *  @param trip       the type of optimization to be performed
   *  @return double    a decimal value representing the string passed as a parameter
   */
  public ShorterTrip(ArrayList<Brewery> destList, type trip) {
    /*this.dests = destList;
    this.mileageTable = new int[dests.size()][dests.size()];
    mileageTable = populateMileageTable();
    this.trip = trip;
    this.distances = new int[dests.size()];*/
    this(destList, trip, Distance.unit.Kilometers);
  }
  /**
   *  the main constructor for shorterTrip
   *  @param destList   the base list of elements to be reordered
   *  @param trip       the type of optimization to be performed
   *  @param type       the units that distance should be calculated with
   *  @return double    a decimal value representing the string passed as a parameter
   */
  public ShorterTrip(ArrayList<Brewery> destList, type trip, Distance.unit type) {
    this.distance = new Distance(type);
    this.dests = destList;
    this.mileageTable = new int[dests.size()][dests.size()];
    mileageTable = populateMileageTable();
    this.trip = trip;
    this.distances = new int[dests.size()];
  }
  /**
   *  a constructor for shorterTrip assuming nearest neighbor optimi
   *  @param destList   the base list of elements to be reordered
   *  @return double    a decimal value representing the string passed as a parameter
   */
  public ShorterTrip(ArrayList<Brewery> destList) {
    this(destList, type.NearestNeighbor);
  }

  public ArrayList<Brewery> computePath() {
    if(trip == type.NoOpt){ //handles no opt trip
      dests.add(dests.get(0));
      return dests;
    }
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
    if(this.trip == type.TwoOpt){ //handles two opt
      twoOpt(path);
    }
    if(this.trip == type.ThreeOpt){ //handles three opt
      threeOpt(path); //handle this later
    }
    /*
    System.out.print("StartNode = " + superStart + ", length = " + path.length+ " path =:");
    for(int ind : path)
      System.out.print("" + ind + " , ");
    System.out.println("");
    */
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
    for (int i = 0; i < mileageTable.length; i++) {
      for (int j = 0; j < mileageTable[i].length; j++) {
        mileageTable[i][j] = distance.greatCircleDistance(dests.get(i), dests.get(j));
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
    int ret = 0;
    for (int i = 0; i < path.size()-1; i++) {
      ret += distance.greatCircleDistance(path.get(i), path.get(i+1));
    }
    return ret;
  }
  public int[] twoOptSwap(int[] path, int  i, int k) { // swap in place
    int temp;
    while(i < k) {
      temp = path[i];
      path[i] = path[k];
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
  public int[] threeOptSwap(int[] path, int i, int j, int k){
    int temp;
    while(j < k) {
      temp = path[i];
      path[i] = path[j];
      path[j] = temp;
      i++; j++;
    }
    return path;
  }

  public int[] threeOpt(int[] path) {
    //System.out.println("in Three opt startNode = " + path[0]);
    boolean improvement = true;
    int [] delta = new int[7];
    int n = path.length;
    int index;
    if (n < 6){
      return path;
    }
    while (improvement) {
      improvement = false;
      // try all less than equals
      for (int i = 1; i < n - 5; i++) { // check n>4
        for (int j = i + 2; j < n - 3; j++) {// This has to be < not <= or we get an out of bounds error because we test k+1
          for (int k = j + 2; k < n - 1; k++) {

            //compute cases
            delta[0] = -mileageTable[path[i]][path[i + 1]] - mileageTable[path[j]][path[j + 1]]
                + mileageTable[path[i]][path[j]] + mileageTable[path[i + 1]][path[j + 1]];
            delta[1] = -mileageTable[path[j]][path[j + 1]] - mileageTable[path[k]][path[k + 1]]
                + mileageTable[path[j]][path[k]] + mileageTable[path[j + 1]][path[k + 1]];
            delta[2] = -mileageTable[path[i]][path[i + 1]] - mileageTable[path[k]][path[k + 1]]
                + mileageTable[path[i]][path[k]] + mileageTable[path[i + 1]][path[k + 1]];
            delta[3] = -mileageTable[path[i]][path[i + 1]] -mileageTable[path[j]][path[j + 1]]
                - mileageTable[path[k]][path[k + 1]] + mileageTable[path[i]][path[j]]
                + mileageTable[path[i + 1]][path[k]] + mileageTable[path[j + 1]][path[k + 1]];
            delta[4] = -mileageTable[path[i]][path[i + 1]] -mileageTable[path[j]][path[j + 1]]
                - mileageTable[path[k]][path[k + 1]] + mileageTable[path[i]][path[k]]
                + mileageTable[path[j + 1]][path[i+1]] + mileageTable[path[j]][path[k + 1]];
            delta[5] = -mileageTable[path[i]][path[i + 1]] -mileageTable[path[j]][path[j + 1]]
                - mileageTable[path[k]][path[k + 1]] + mileageTable[path[i]][path[j+1]]
                + mileageTable[path[k]][path[j]] + mileageTable[path[i + 1]][path[k + 1]];
            delta[6] = -mileageTable[path[i]][path[i + 1]] -mileageTable[path[j]][path[j + 1]]
                - mileageTable[path[k]][path[k + 1]] + mileageTable[path[i]][path[j + 1]]
                + mileageTable[path[k]][path[i + 1]] + mileageTable[path[j]][path[k + 1]];
            //get max index
            index = 0;
            for(int p = 0; p < delta.length; p++){
              if ((delta[p] < delta[index]) && (delta[p] < 0)){
                index = p;
              }
            }
            //System.out.println("delta = " + delta[index]);
            if (delta[index] < 0) {//improvement?
              if (index == 0) { //case 1 from slides
                path = twoOptSwap(path, i + 1, j);
              }else if (index == 1) { //case 2 from slides
                path = twoOptSwap(path, j + 1, k);
              }else if (index == 2) { //case 3 from slides
                path = twoOptSwap(path, i + 1, k);
              }else if (index == 3) { //case 4 from slides
                path = twoOptSwap(path, i + 1, j);
                path = twoOptSwap(path, j + 1, k);
              }else if (index == 4) { //case 5 from slides //TODO here down
                path = twoOptSwap(path, j + 1, k);
                threeOptSwap(path, i, j, k);
              }else if (index == 5) { //case 6 from slides
                path = twoOptSwap(path, i + 1, j);
                threeOptSwap(path, i, j, k);
              }else if (index == 6) { //case 7 from slides
                path = twoOptSwap(path, i + 1, k);
                threeOptSwap(path, i, j, k);
              }
              improvement = true;
            }
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