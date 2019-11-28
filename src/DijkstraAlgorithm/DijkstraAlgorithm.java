package DijkstraAlgorithm;

import java.io.*;
import java.util.*;

public class DijkstraAlgorithm {

    static int vertexNum = 0;           // 정점 개수
    static int edgeNum = 0;             // 간선 개수
    static int[] dist = null;           // 최단 거리 저장 배열
    static ArrayList<Edge>[] edge;      // 그래프 리스트
    static List<Integer> pointList;     // txt 읽어와 저장할 점의 리스트
    static List<PointInfo> point;       // 점의 정보(시작점, 도착점, 가중치) 저장될 리스트

    final int INFINITY = Integer.MAX_VALUE;         // 무한대 값

    public void insert(File F) throws FileNotFoundException {
        /** File read & insert */
        FileReader fr = new FileReader(F);
        BufferedReader br = new BufferedReader(fr);

        String[] temp = null, temp_p = null;
        pointList = new ArrayList<>();
        point = new ArrayList<>();

        try {
            /** txt 파일의 첫 줄 : 정점 정보 */
            String firstLine = br.readLine();
            temp_p = firstLine.split(",");
            for (int i = 0; i < temp_p.length; i++)
                pointList.add(Integer.parseInt(temp_p[i]));

            String line = "";
            while ((line = br.readLine()) != null) {
                temp = line.split(",");
                PointInfo p = new PointInfo(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
                point.add(p);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println(e);
        }

        vertexNum = pointList.size();
        edgeNum = point.size();
        dist = new int[vertexNum + 1];
        edge = new ArrayList[vertexNum + 1];

        init(); // 초기화

        /** 그래프 연결 */
        for (int i = 0; i < edgeNum; i++) {
            int u = point.get(i).src;
            int v = point.get(i).dest;
            int w = point.get(i).weight;
            edge[u].add(new Edge(v, w));
        }

        /** 실행 & 출력문 */
        System.out.println("Dijkstra's Algorithm으로 계산한 결과는 다음과 같습니다.\n");
        Dijkstra(1);       // 1번 정점부터 시작
    }

    private void init() {
        for (int i = 1 ; i <= vertexNum; i++) {
            edge[i] = new ArrayList<Edge>();
            dist[i] = INFINITY;                 // cost 배열 무한대로 초기화
        }
    }

    private void Dijkstra(int s) {

        dist[s] = 0;        // 시작 정점 최단 거리 초기화

        PriorityQueue<Edge> Q = new PriorityQueue<Edge>();  // 우선 순위 큐 Q
        ArrayList<Edge> S = new ArrayList<Edge>();          // 방문 여부 S

        Q.add(new Edge(s, dist[s]));              // 시작 정점과 자기자신으로 가는 거리를 큐에 처음 넣어줌

        int indexS = 0;                 // S counter
        while (S.size() < 5) {
            int indexQ = 0;             // Q counter
            Edge curr = Q.remove();     // root 추출
            S.add(curr);                // S에 삽입

            System.out.printf("==============================================");
            System.out.printf("\n S[%d] : d[%s] = %d", indexS, alpha(S.get(indexS).dest), dist[curr.dest]);
            System.out.printf("\n----------------------------------------------");

            /** 연결된 정점들 반복 */
            for (int i = 0; i < edge[curr.dest].size(); i++) {
                Edge next = edge[curr.dest].get(i);
                /** d[u] + w[u][v] < d[v] */
                if (dist[curr.dest] != INFINITY && dist[curr.dest] + next.weight < dist[next.dest]) {
                    System.out.printf("\n Q[%d] : d[%s] = %d", indexQ, alpha(next.dest), dist[next.dest]);
                    dist[next.dest] = dist[curr.dest] + next.weight;        // d[v] 갱신
                    System.out.printf(" -> d[%s] = %d", alpha(next.dest), dist[next.dest]); // 갱신 값 출력
                    Q.add(next);        // Q에 next 추가
                }
                indexQ++;
            }
            indexS++;
            System.out.printf("\n\n");
        }

        /** final result print */
        System.out.println("Graph의 최단 경로 cost");
        for (int i = 0; i < S.size(); i++) {
            System.out.printf(" %s -> %s : %d\n", alpha(S.get(s - 1).dest), alpha(S.get(i).dest), dist[S.get(i).dest]);
        }
    }

    /** 알파벳 반환 */
    private String alpha(int num) {
        String alpha[] = {"A", "B", "C", "D", "E"};
        return alpha[num - 1];
    }

}
