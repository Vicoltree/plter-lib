/**
   Copyright [plter] [xtiqin]
   http://plter.sinaapp.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

   This is a part of PlterAndroidLib on 
   http://plter.sinaapp.com/?cat=14 
	https://github.com/xtiqin/plter-android-lib
 */

package com.plter.lib.java.event;

import java.util.ArrayList;
import java.util.List;

public class EventListenerList<E extends Event> {

	public void add(EventListener<E> listener){
		eList.add(listener);
	}
	
	public void add(EventListener<E> listener,int index){
		eList.add(index, listener);
	}
	
	public void remove(EventListener<E> listener){
		eList.remove(listener);
	}
	
	public void remove(){
		eList.clear();
	}
	
	public boolean dispatch(Object target,E event){
		
		boolean suc = true;
		
		for (int i = 0; i < eList.size(); i++) {
			if (!eList.get(i).onReceive(target, event)) {
				suc=false;
			}
			
			if (event.isStoped()) {
				event.reset();
				break;
			}
		}
		
		event.recycle();
		return suc;
	}
	
	
	public boolean dispatch(E event){
		return dispatch(null,event );
	}
	
	private final List<EventListener<E>> eList = new ArrayList<EventListener<E>>();
	
}
