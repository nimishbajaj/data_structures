package week1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

class Request {
    public int arrival_time;
    public int process_time;

    public Request(int arrival_time, int process_time) {
        this.arrival_time = arrival_time;
        this.process_time = process_time;
    }
}

class Response {
    public boolean dropped;
    public int start_time;

    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }
}

class Buffer {
    private int size_;
    private Deque<Integer> finish_time_;

    public Buffer(int size) {
        this.size_ = size;
        this.finish_time_ = new LinkedList<>();
    }

    public Response Process(Request request) {
        // write your code here

        // remove all the requests which have been processed already
        while (!finish_time_.isEmpty() && finish_time_.peek() <= request.arrival_time) {
            finish_time_.poll();
        }

        Response response = null;

        int lastProcessed = (finish_time_.size() > 0) ? finish_time_.getLast() : request.arrival_time;
        if (finish_time_.isEmpty() || finish_time_.size() < size_) {
            response = new Response(false, lastProcessed);
            finish_time_.offer(lastProcessed + request.process_time);
        } else {
            response = new Response(true, lastProcessed);
        }

        return response;
    }
}

class process_packages {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.start_time);
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        while (true){
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
//        }
    }
}
