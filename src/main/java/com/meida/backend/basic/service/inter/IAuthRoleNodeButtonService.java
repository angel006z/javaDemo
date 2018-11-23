package com.meida.backend.basic.service.inter;

import java.util.UUID;

public interface IAuthRoleNodeButtonService {

	boolean isAuthRoleNodeButton(UUID iUSERID, int iCurrentPageNodeId, int iCurrentButtonId, boolean isSuper);
}
