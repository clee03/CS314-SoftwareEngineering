package edu.csu2017fa314.T17.Model;

import java.util.ArrayList;

public class ShorterTrip {
  protected int[][] mileageTable;
  protected ArrayList<Brewery> dests;
  protected type trip;

  public enum type{NearestNeighbor, TwoOpt}

  public ShorterTrip(){
  }

  public ShorterTrip(ArrayList<Brewery> destList, type trip) {
    this.dests = destList;
    this.mileageTable = new int[dests.size()][dests.size()];
    mileageTable = populateMileageTable();
    this.trip = trip;
  }

  public ShorterTrip(ArrayList<Brewery> destList) {
    this(destList, type.NearestNeighbor);
  }

  public ArrayList<Brewery> computePath() {
    ArrayList<Brewery> pathList = new ArrayList<>();
    int min = Integer.MAX_VALUE;
    int startNode = 0;
    for (int j = 0; j < dests.size(); j++) {
      int k = pathDistance(this.pathFromAlpha(j));
      if (min > k) {
        min = k;
        startNode = j;
      }
    }
    ArrayList<Integer> pathFromA = pathFromAlpha(startNode);
    for (int i = 0; i < pathFromA.size(); i++) {
      pathList.add(dests.get(pathFromA.get(i)));
    }
    return pathList;
  }

  public ArrayList<Integer> pathFromAlpha(int startNode) {
    ArrayList<Integer> validNodes = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();
    int superStart = startNode;
    for (int i = 0; i < dests.size(); i++) {
      validNodes.add(i);
    }
    validNodes.remove(validNodes.indexOf(superStart));
    path.add(superStart);
    while (!validNodes.isEmpty()) {
      startNode = minIndex(startNode, validNodes);
      path.add(startNode);
    }
    path.add(superStart);
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

  public int pathDistance(ArrayList<Integer> path) {
    int ret = 0;
    for (int i = 0; i < path.size()-1; i++) {
      ret += mileageTable[path.get(i)][path.get(i + 1)];
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
  public ArrayList<Integer> twoOptSwap(ArrayList<Integer> path, int  i1, int k) { // swap in place
    int temp;
    while(i1 < k) {
      temp = path.get(i1);
      path.set(i1, path.get(k));
      path.set(k, temp);
      i1++; k--;
    }
    return path;
  }
  public ArrayList<Integer> twoOpt(ArrayList<Integer> path) {
    boolean improvement = true;
    int delta;
    int n = mileageTable.length;
    //System.out.println(path);
    while (improvement) {
      //System.out.println("here");
      improvement = false;
      for (int i = 0; i <= n - 3; i++) { // check n>4
        for (int k = i + 2; k < n - 1; k++) {// This has to be < not <= or we get an out of bounds error because we test k+1
          delta = -mileageTable[path.get(i)][path.get(i+1)] - mileageTable[path.get(k)][path.get(k+1)]
              + mileageTable[path.get(i)][path.get(k)] + mileageTable[path.get(i+1)][path.get(k+1)];
          if (delta < 0) { //improvement?
            path = twoOptSwap(path, i+1, k);
            //System.out.println("swapping " + i + ", " + k);
            improvement = true;
          }
        }
      }
    }
    return path;
  }
}
