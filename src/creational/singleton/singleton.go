// Singleton is a creational design pattern that lets you ensure that a class has only one instance,
// while providing a global access point to this instance.

package singleton

import (
	"fmt"
	"sync"
)

var lock = &sync.Mutex{}

type singletonClass struct {
}

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

func SingletonExample() {
	for i := 0; i < 30; i++ {
		go getInstance()
	}
	fmt.Scanln()
}
