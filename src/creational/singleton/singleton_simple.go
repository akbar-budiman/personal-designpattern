/*
	Singleton is a creational design pattern that lets you ensure that a class has only one instance,
	while providing a global access point to this instance.

	Don't depend directly on singleton.
	Depend on some interface that singleton implements
*/

package singleton

import (
	"fmt"
	"sync"
	"time"
)

var lock = &sync.Mutex{}
var once sync.Once

type singletonClass struct{}

var singletonInstance *singletonClass

func getInstance() *singletonClass {
	if singletonInstance == nil {
		lock.Lock()
		defer lock.Unlock()
		if singletonInstance == nil {
			fmt.Println("Creating single instance now.")
			singletonInstance = &singletonClass{}
		} else {
			fmt.Println("Single instance already created.")
		}
	} else {
		fmt.Println("Single instance already created.")
	}
	return singletonInstance
}

func getInstanceWithSync() {
	if singletonInstance == nil {
		once.Do(
			func() {
				fmt.Println("Creating single instance now.")
				singletonInstance = &singletonClass{}
			},
		)
	} else {
		fmt.Println("Single instance already created.")
	}
}

func Example() {
	for i := 0; i < 5; i++ {
		go getInstance()
	}
	for i := 0; i < 5; i++ {
		go getInstanceWithSync()
	}
	time.Sleep(time.Second)
	//fmt.Scanln()
}
