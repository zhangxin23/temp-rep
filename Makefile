
default:epoll_test.out

epoll_test.out:epoll_test.o
	gcc -g -o epoll_test.out epoll_test.c

epoll_test.o:epoll_test.c
	gcc -g -c epoll_test.c

clean:
	rm -rf epoll_test.out epoll_test.o
